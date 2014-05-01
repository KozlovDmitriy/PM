class AnalysisControllerController < ApplicationController
  def index
    require_relative '../../lib/tcp_client'
    client = TcpClient.new 50125, 'localhost'
    @params = {:impl_plan => client.impl_plan, :av_check => client.av_check,
               :items_count => client.items_count, :total_checks_count => client.total_checks_count}
    client.close
  end

  # POST /analyse/problems.json
  def analyse_problems
    require_relative '../../lib/tcp_client'
    client = TcpClient.new 50125, 'localhost'

    problems = client.analise_params [{:name => 'ImplPlan', :value => params[:impl_plan]},
                           {:name => 'AvCheck', :value => params[:av_check]},
                           {:name => 'ItemsCount', :value => params[:items_count]},
                           {:name => 'TotalChecksCount', :value => params[:total_checks_count]}]

    client.close
    render :json => problems
  end

  # POST /analyse/solutions.json
  def analyse_solution
    require_relative '../../lib/tcp_client'
    client = TcpClient.new 50125, 'localhost'

    solutions = client.analise_problems [:name => params[:problems], :value => params[:problems]]
    client.close
    render :json => solutions
  end

  def new_index
    @consultants = Consultant.all
  end
end
