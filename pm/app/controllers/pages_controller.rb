class PagesController < ApplicationController
  def index
    require_relative '../../lib/tcp_client'
    client = TcpClient.new 50125, 'localhost'

    client.analise_params nil
    #client.analise_problems nil
    client.close
  end
end
