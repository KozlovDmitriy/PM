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

  def new_analyse_problems
    require_relative '../../lib/tcp_client'
    client = TcpClient.new 50125, 'localhost'
    date_id = params[:date].to_i

    problems = client.analise_params [{:name => 'ImplPlan', :value => get_impl_plan_value(params[:consultant][:main][:impl].to_f)},
                                      {:name => 'AvCheck', :value => get_av_check_value(params[:consultant][:main][:avCheck].to_f)},
                                      {:name => 'ItemsCount', :value => get_items_count_value(params[:consultant][:main][:itemsCount].to_f)},
                                      {:name => 'TotalChecksCount', :value => get_total_checks_value(params[:consultant][:main][:totalChecks].to_f)}]

    client.close
    complex_text = ''
    problems[:value].each do |item|
      arr = Problem.where :description => item
      complex_text = "#{complex_text} #{item} И"
      if arr.blank?
        problem = Problem.create :description => item
      else
        problem = arr.first
      end
      if AnalysisProblemConnect.where(:problem_id => problem.id,
                                      :analysis_id => date_id,
                                      :consultant_id => params[:consultant][:id].to_i).blank?
        AnalysisProblemConnect.create :problem_id => problem.id,
                                      :analysis_id => date_id,
                                      :consultant_id => params[:consultant][:id].to_i
      else
        objs = AnalysisProblemConnect.where(:problem_id => problem.id,
                                            :analysis_id => date_id,
                                            :consultant_id => params[:consultant][:id].to_i)
        objs.each {|object| object.destroy }
        AnalysisProblemConnect.create :problem_id => problem.id,
                                      :analysis_id => date_id,
                                      :consultant_id => params[:consultant][:id].to_i
      end
    end
    sol = Problem.new :description => complex_text, :problem_type => 'complex'
    sol.cut_description!
    cs = Problem.where :description => sol.description
    if cs.blank? and problems[:value].count > 1
      sol.save
    end
    render :json => problems
  end

  # POST /analyse/solutions.json
  def analyse_solution
    require_relative '../../lib/tcp_client'
    client = TcpClient.new 50125, 'localhost'
    date_id = params[:date].to_i
    solutions = client.analise_problems [:name => params[:problems], :value => params[:problems]]
    client.close
    complex_text = ''
    solutions[:value].each do |item|
      array = Solution.where :description => item
      complex_text = "#{complex_text} #{item} И"
      if array.blank?
        solution = Solution.create :description => item, :solution_type => 'simple'
      else
        solution = array.first
      end
      AnalysisSolutionConnect.create :solution_id => solution.id,
                                     :analysis_id => date_id,
                                     :consultant_id => params[:consultant][:id].to_i
    end
    sol = Solution.new :description => complex_text, :solution_type => 'complex'
    sol.cut_description!
    cs = Solution.where :description => sol.description
    if cs.blank? && solutions[:value].count > 1
      sol.save
    end
    render :json => solutions
  end

  def new_index
    @consultants = Consultant.all
    @analysis = Analysis.all.order :id => :desc

    respond_to do |format|
      format.html
      format.json {
        @analyse = Analysis.find params[:analyse_id].to_i
        @param_values = ParamValue.where :date_id => @analyse.id
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
      if i_value >= item['min'].to_f and i_value <= item['max'].to_f
        return item['uri']
      end
    end
    'http://www.owl-ontologies.com/PersonalManagement.owl#ImplPlan_4'
  end

  def get_av_check_value i_value
    require 'yaml'
    thing = YAML.load_file "#{Rails.root}/config/av_check.yml"
    thing.each do |item|
      if i_value >= item['min'].to_f and i_value <= item['max'].to_f
        return item['uri']
      end
    end
    'http://www.owl-ontologies.com/PersonalManagement.owl#AvCheck_43'
  end

  def get_items_count_value i_value
    require 'yaml'
    thing = YAML.load_file "#{Rails.root}/config/items_count.yml"
    thing.each do |item|
      if i_value == item['val'].to_f
        return item['uri']
      end
    end
    'http://www.owl-ontologies.com/PersonalManagement.owl#ItemsCount_1'
  end

  def get_total_checks_value i_value
    require 'yaml'
    thing = YAML.load_file "#{Rails.root}/config/total_checks.yml"
    thing.each do |item|
      if i_value >= item['min'].to_f and i_value <= item['max'].to_f
        return item['uri']
      end
    end
    'http://www.owl-ontologies.com/PersonalManagement.owl#TotalChecksCount_92'
  end
end

