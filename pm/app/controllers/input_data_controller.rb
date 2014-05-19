class InputDataController < ApplicationController
  before_action :set_input_datum, only: [:show, :edit, :update, :destroy]

  # GET /input_data
  # GET /input_data.json
  def index
    @input_data = InputDatum.all
  end

  # GET /input_data/1
  # GET /input_data/1.json
  def show
  end

  # Парсинг excel файла.
  def parse_excel

    filename = "#{Rails.root}/upload/input/#{params[:file].original_filename}"
    File.open(filename, 'wb') {
        |file| file.write params[:file].read
    }

    require_relative '../../lib/read_excel'
    ods = ReadExcel.new filename
    File.open('debug.txt', 'a') { |file| file.write ods.get_params.to_yaml }
    
    analise = Analysis.new :date => Date.today
    analise.save
    result = []
    ods.get_params.each do |item|
      
      consultant_family = item[:fio].match(/^[А-Я][а-я]+/).to_s
      consultant = Consultant.find_by(:family_name => consultant_family)
      hash = {}
      hash[:id] = consultant.id
      hash[:fio] = consultant.full_name
      hash[:url] = ''
      hash[:main] = {}
      hash[:main][:indPlan] = item[:ind_plan]
      hash[:main][:implPlan] = item[:impl_plan]
      hash[:main][:impl] = item[:impl]
      hash[:main][:avCheck] = item[:av_check]
      hash[:main][:itemsCount] = item[:items]
      hash[:main][:totalChecks] = item[:total_checks]
      hash[:additional] = {}
      hash[:additional][:holiday] = false
      hash[:additional][:hospital] = false
      hash[:additional][:mleave] = false
      hash[:additional][:exp] = false
      hash[:additional][:dismissal] = false

      result.push hash
    end

    render :json => result.uniq
  end

  # GET /input_data/new
  def new
    @input_datum = InputDatum.new

    @consultants = Consultant.all
    @params_main = Param.where(:role => 'main').order(:sorting => :asc)
    @params_add = Param.where(:role => 'additional').order(:sorting => :asc)
  end

  def save_form_params
    # /save_params

    analyse = Analysis.new :date => params[:date]
    if params[:isFinish]
      analyse.status = 'full_data'
    else
      analyse.status = 'not_full_data'
    end
    analyse.save

    # Сохранение плана магазина.
    DepartmentPlan.create :date_id => analyse.id,
                          :plan => params[:department][:plan].to_f,
                          :value => params[:department][:value].to_f

    params[:params].each do |item|
      c_id = item[:id].to_i
      a_id = analyse.id
      # Индивидуальный план
      v1 = ParamValue.new(:value => item[:main][:indPlan], :param_id => 1, :date_id => a_id, :consultant_id => c_id)
      v1.save
      # Выполнение плана
      v2 = ParamValue.new(:value => item[:main][:implPlan], :param_id => 2, :date_id => a_id, :consultant_id => c_id)
      v2.save
      # Выполнение
      v3 = ParamValue.new :value => item[:main][:impl], :param_id => 3, :date_id => a_id, :consultant_id => c_id
      v3.save
      # Средний чек
      v4 = ParamValue.new :value => item[:main][:avCheck], :param_id => 4, :date_id => a_id, :consultant_id => c_id
      v4.save
      # Количество позиций в чеке
      v5 = ParamValue.new :value => item[:main][:itemsCount], :param_id => 5, :date_id => a_id, :consultant_id => c_id
      v5.save
      # Общее количество чеков
      v6 = ParamValue.new :value => item[:main][:totalChecks], :param_id => 6, :date_id => a_id, :consultant_id => c_id
      v6.save
      # Отпуск
      v7 = ParamValue.create :value => item[:additional][:holiday], :param_id => 7, :date_id => a_id, :consultant_id => c_id
      # Больничный
      v8 = ParamValue.create :value => item[:additional][:hospital], :param_id => 8, :date_id => a_id, :consultant_id => c_id
      # Декретный отпуск
      v9 = ParamValue.create :value => item[:additional][:mleave], :param_id => 9, :date_id => a_id, :consultant_id => c_id
      # Низкий стаж
      v10 = ParamValue.create :value => item[:additional][:exp], :param_id => 10, :date_id => a_id, :consultant_id => c_id
      # Увольнение
      v11 = ParamValue.create :value => item[:additional][:dismissal], :param_id => 11, :date_id => a_id, :consultant_id => c_id
    end

    render :json => true
  end

  # GET /input_data/1/edit
  def edit
  end

  # POST /input_data
  # POST /input_data.json
  def create
    @input_datum = InputDatum.new(input_datum_params)

    respond_to do |format|
      if @input_datum.save
        format.html { redirect_to @input_datum, notice: 'Input datum was successfully created.' }
        format.json { render action: 'show', status: :created, location: @input_datum }
      else
        format.html { render action: 'new' }
        format.json { render json: @input_datum.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /input_data/1
  # PATCH/PUT /input_data/1.json
  def update
    respond_to do |format|
      if @input_datum.update(input_datum_params)
        format.html { redirect_to @input_datum, notice: 'Input datum was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @input_datum.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /input_data/1
  # DELETE /input_data/1.json
  def destroy
    @input_datum.destroy
    respond_to do |format|
      format.html { redirect_to input_data_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_input_datum
      @input_datum = InputDatum.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def input_datum_params
      params.require(:input_datum).permit(:date)
    end
end
