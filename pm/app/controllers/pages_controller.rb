class PagesController < ApplicationController
  def index
    require_relative '../../lib/tcp_client'
    client = TcpClient.new 50125, 'localhost'

    client.analise_params [{:name => 'implPlan', :value => 100},
                           {:name => 'AvCheck', :value => 1233.22},
                           {:name => 'Name', :value => 'Alex'}]
    client.analise_problems nil
    client.close
  end
end
