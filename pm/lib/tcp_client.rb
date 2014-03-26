
require 'socket'

# Класс доступа к Java TCP серверу.
class TcpClient

  # Поля классов
  attr_accessor :port,  # Порт сервера.
                :host,  # Хост сервера.
                :socket # Сокет для связи с сервером.

  # Конструктор.
  def initialize port, host
    @host, @port = host, port
    @socket = TCPSocket.open @host, @port
  end

end