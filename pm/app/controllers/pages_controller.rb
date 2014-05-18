class PagesController < ApplicationController
  def index
    #require_relative '../../lib/tcp_client'
    #client = TcpClient.new 50125, 'localhost'
    #
    #client.analise_params [{:name => 'ImplPlan', :value => 'ImplPlan_24'},
    #                       {:name => 'AvCheck', :value => 'AvCheck_58'},
    #                       {:name => 'ItemsCount', :value => 'ItemsCount_20'},
    #                       {:name => 'TotalChecksCount', :value => 'TotalChecksCount_112'}]
    #client.analise_problems [:name => 'CDGI', :value => 'CDGI']
    #client.close
  end

  def department_plans
    @result = DepartmentPlan.all.order(:date_id => :desc).take 6

    render 'department_plans'
  end

  def month
  end

  def chart
    
    first = params[:first].to_i
    last = params[:last].to_i

    @result = DepartmentPlan.where(:date_id => first..last).order(:date_id => :asc)

    render 'department_plans.json'
  end

end
