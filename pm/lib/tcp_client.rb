
require 'socket'

# Класс доступа к Java TCP серверу.
class TcpClient

  # Поля классов
  attr_accessor :port,  # Порт сервера.
                :host,  # Хост сервера.
                :socket # Сокет для связи с сервером.

  # Константы связи с Java сервером.
  # Конец общения.
  # Блок параметров работы консультантов.
  # Блок проблем в работе консультантов.
  # Ответ в виде множества проблем.
  # Ответ в виде множества рекомендаций.

  # Конструктор.
  def initialize port, host
    @host, @port = host, port
    @socket = TCPSocket.open @host, @port
  end

end