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

    respond_to do |format|
      format.html
      format.json {
        analyse = Analysis.find params[:analyse_id].to_i
        @param_values = ParamValue.where :date_id => analyse.id
      }
    end

  end

  def get_new_index
    analyse = Analysis.find params[:analyse_id].to_i

    respond_to do |format|
      format.json { render 'get_new_index' }
    end
  end

  private

  # Метод получения значения выполнения для онтологии.
  def get_impl_plan_value i_value
    require 'yaml'
    thing = YAML.load_file "#{Rails.root}/config/impl_plan.yml"
    thing.each do |item|
      if i_value >= item['min'].to_i and i_value <= item['max'].to_i
        return item['uri']
      end
    end
    'http://www.owl-ontologies.com/PersonalManagement.owl#ImplPlan_4'
  end

  def get_av_check_value i_value
    require 'yaml'
    thing = YAML.load_file "#{Rails.root}/config/av_check.yml"
    thing.each do |item|
      if i_value >= item['min'].to_i and i_value <= item['max'].to_i
        return item['uri']
      end
    end
    'http://www.owl-ontologies.com/PersonalManagement.owl#AvCheck_43'
  end

  def get_items_count_value i_value
    require 'yaml'
    thing = YAML.load_file "#{Rails.root}/config/items_count.yml"
    thing.each do |item|
      if i_value == item['val'].to_i
        return item['uri']
      end
    end
    'http://www.owl-ontologies.com/PersonalManagement.owl#ItemsCount_1'
  end

  def get_total_checks_value i_value
    require 'yaml'
    thing = YAML.load_file "#{Rails.root}/config/total_checks.yml"
    thing.each do |item|
      if i_value >= item['min'].to_i and i_value <= item['max'].to_i
        return item['uri']
      end
    end
    'http://www.owl-ontologies.com/PersonalManagement.owl#TotalChecksCount_92'
  end
end

