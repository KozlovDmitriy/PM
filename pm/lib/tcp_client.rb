
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
    @socket.puts PARAMS_BLOCK_START
    params_hash.each { |item| @socket.puts item[:value]; log "write param #{item[:name]}" }
    @socket.puts PARAMS_BLOCK_END
    response = @socket.readline

    log "Read value: #{response}"

    if response.to_i == RESPONSE_PROBLEM_BLOCK_START
      log 'Read problems start!'
      until response.to_i == RESPONSE_PROBLEM_BLOCK_END
        response = @socket.gets
        log "Read value: #{response}"
      end
      log 'Read problems end!'
    end

    log 'Analise params end!'
  end

  # Метод проведения поиска рекомендаций.
  def analise_problems problems_hash
    raise 'Connection already closed' if @is_close
    log 'Analise problems start!'
    @socket.puts PROBLEM_BLOCK_START
    problems_hash.each { |item| @socket.puts item[:value]; log "write param #{item[:name]}" }
    @socket.puts PROBLEM_BLOCK_END
    response = @socket.gets

    log "Read value: #{response}"

    if response.to_i == RESPONSE_SOLUTION_BLOCK_START
      log 'Read solutions start!'
      until response.to_i == RESPONSE_SOLUTION_BLOCK_END
        response = @socket.gets
        log "Read value: #{response}"
      end
      log 'Read solutions end!'
    end

    log 'Analise problems end!'
  end

  private
  # Закрытый метод ведения лога.
  def log message
    File.open("#{Rails.root}/log/rails_client.log", 'a') {|file| file.write "#{Time.now}: #{message}\n" }
  end

end