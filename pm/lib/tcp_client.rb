
require 'socket'

# Класс доступа к Java TCP серверу.
class TcpClient

  # Поля классов
  attr_accessor :port,  # Порт сервера.
                :host,  # Хост сервера.
                :socket # Сокет для связи с сервером.

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
  end

end