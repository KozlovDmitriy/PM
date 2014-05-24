
require 'socket'

# Класс доступа к Java TCP серверу.
class TcpClient

  # Поля классов
  attr_accessor :port,    # Порт сервера.
                :host,    # Хост сервера.
                :socket,  # Сокет для связи с сервером.
                :is_close # Флаг закрытия соединения.

  # Константы связи с Java сервером.
  # Конец общения.
  END_CONNECTION = 0x01
  # Блок параметров работы консультантов.
  PARAMS_BLOCK_START = 0x02 # Начало.
  PARAMS_BLOCK_END = 0x03 # Конец.
  # Блок проблем в работе консультантов.
  PROBLEM_BLOCK_START = 0x04 # Начало.
  PROBLEM_BLOCK_END = 0x05 # Конец.
  # Ответ в виде множества проблем.
  RESPONSE_PROBLEM_BLOCK_START = 0x06 # Начало.
  RESPONSE_PROBLEM_BLOCK_END = 0x07 # Конец.
  # Ответ в виде множества рекомендаций.
  RESPONSE_SOLUTION_BLOCK_START = 0x08 # Начало.
  RESPONSE_SOLUTION_BLOCK_END = 0x09 # Конец.
  # Запрос на получение всех значений выполнения индивидуального плана.
  IMPL_PLAN = 0x10
  # Запрос на получение всех значений среднего чека.
  AV_CHECK = 0x11
  # Запрос на получение всех значений количества позиций в чеке.
  ITEMS_COUNT = 0x12
  # Запрос на получение общего числа чеков.
  TOTAL_CHECKS_COUNT = 0x13
  # Запрос на создание новой проблемы.
  CREATE_NEW_PROBLEM = 0x14
  # Запрос на создание новой рекомендации.
  CREATE_NEW_SOLUTION = 0x15

  # Метод создания новой проблемы в решателе.
  def create_new_problem problem
    raise 'Connection already closed!' if @is_close
    log 'Create new problem start'
    @socket.puts CREATE_NEW_PROBLEM
    @socket.puts problem.problem_type
    @socket.puts problem.description
    log 'Create new problem end'
  end

  # Метод создания новой рекомендации в решателе.
  def create_new_solution solution
    rails 'Connection already closed!' if @is_close
    log 'Create new solution start'
    @socket.puts CREATE_NEW_SOLUTION
    @socket.puts solution.solution_type
    @socket.puts solution.description
    log 'Create new solution end'
  end

  # Метод получения всех значений общего числа чеков в онтологии.
  # @return [Array] - массив хешей из значения и uri параметра в онтологии.
  def total_checks_count
    raise 'Connection already closed!' if @is_close
    log 'Get all values of total checks count start'
    hash = read_param TOTAL_CHECKS_COUNT
    log 'Get all values of total checks count end'
    hash
  end

  # Метод получения всех значений количества позиций в чеке в онтологии.
  # @return [Array] - массив хешей из значения и uri параметра в онтологии.
  def items_count
    raise 'Connection already closed!' if @is_close
    log 'Get all values of items count start'
    hash = read_param ITEMS_COUNT
    log 'Get all values of items count end'
    hash
  end

  # Метод получения всех значений среднего чека из онтологии.
  # @return [Array] - массив хешей из значения и uri параметра в онтологии.
  def av_check
    raise 'Connection already closed!' if @is_close
    log 'Get all values of av. check start'
    hash = read_param AV_CHECK
    log 'Get all values of av. check end'
    hash
  end

  # Метод получения всех значений выполнения плана из онтологии.
  # @return [Array] - массив хешей из значения и uri параметра в онтологии.
  def impl_plan
    raise 'Connection already closed!' if @is_close
    log 'Get all values of impl. plan start'
    hash = read_param IMPL_PLAN
    log 'Get all values of impl. plan end'
    hash
  end

  # Конструктор.
  def initialize port, host
    @host, @port = host, port
    @socket = TCPSocket.open @host, @port
    @socket.set_encoding 'UTF-8'
    @is_close = false
    log 'Connection open!'
  end

  # Метод закрытия соединения с сервером.
  def close
    raise 'Connection already closed!' if @is_close
    @socket.puts END_CONNECTION
    @socket.close
    log 'Connection close!'
  end

  # Метод проведения поиска проблем.
  def analise_params params_hash
    raise 'Connection already closed!' if @is_close
    log 'Analise params start'
    result = []
    @socket.puts PARAMS_BLOCK_START
    params_hash.each { |item| @socket.puts item[:value]; log "write param #{item[:name]}" }
    @socket.puts PARAMS_BLOCK_END
    response = @socket.readline

    log "Read value: #{response}"

    if response.to_i == RESPONSE_PROBLEM_BLOCK_START
      log 'Read problems start!'
      response = @socket.gets
      until response.to_i == RESPONSE_PROBLEM_BLOCK_END
        result.push response
        response = @socket.gets
        log "Read value: #{response}"
      end
      log 'Read problems end!'
    end

    log 'Analise params end!'
    {:uri => result.shift, :value => result}
  end

  # Метод проведения поиска рекомендаций.
  def analise_problems problems_hash
    raise 'Connection already closed' if @is_close
    log 'Analise problems start!'
    result = []
    @socket.puts PROBLEM_BLOCK_START
    problems_hash.each { |item| @socket.puts item[:value]; log "write param #{item[:name]}" }
    @socket.puts PROBLEM_BLOCK_END
    response = @socket.gets

    log "Read value: #{response}"

    if response.to_i == RESPONSE_SOLUTION_BLOCK_START
      log 'Read solutions start!'
      response = @socket.gets
      until response.to_i == RESPONSE_SOLUTION_BLOCK_END
        result.push response
        response = @socket.gets
        log "Read value: #{response}"
      end
      log 'Read solutions end!'
    end

    log 'Analise problems end!'
    {:uri => result.shift, :value => result}
  end

  private
  # Закрытый метод ведения лога.
  def log message
    File.open("#{Rails.root}/log/rails_client.log", 'a') {|file| file.write "#{Time.now}: #{message}\n" }
  end

  # Закрытый метод чтения параметра из owl.
  # @param const [int] - Константа тип считываемого параметра.
  # @return [Array] - все значения параметра из онтологии.
  def read_param const
    @socket.puts const
    response = @socket.readline
    require 'json'
    hash = JSON.parse response
  end

end