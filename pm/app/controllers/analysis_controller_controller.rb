class AnalysisControllerController < ApplicationController
  def index
    require_relative '../../lib/tcp_client'
    client = TcpClient.new 50125, 'localhost'
    @params = {:impl_plan => client.impl_plan, :av_check => client.av_check,
               :items_count => client.items_count, :total_checks_count => client.total_checks_count}
    client.close
  end
end
