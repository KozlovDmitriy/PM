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
    
    analise = Analysis.new :date => Date.today
    analise.save
    ods.get_params.each do |item|
      
      consultant_family = item[:fio].match(/^[А-Я][а-я]+/).to_s
      pv = ParamValue.new :param_id => Param.find_by(:name => 'Индивидуальный план').id, 
                          :value => item[:ind_plan],
                          :date_id => analise.id,
                          :consultant_id => Consultant.find_by(:family_name => consultant_family).id
      
      File.open('debug.txt', 'a') { |file| file.write pv.to_yaml }
    end

    render :json => ods.get_params
  end

  # GET /input_data/new
  def new
    @input_datum = InputDatum.new

    @consultants = Consultant.all
    @params_main = Param.where(:role => 'main').order(:sorting => :asc)
    @params_add = Param.where(:role => 'additional').order(:sorting => :asc)
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
