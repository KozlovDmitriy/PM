class ConfiguresController < ApplicationController
  before_action :set_configure, only: [:show, :edit, :update, :destroy]

  # GET /configures
  # GET /configures.json
  def index
    @configures = Configure.all
  end

  # GET /configures/1
  # GET /configures/1.json
  def show
  end

  # GET /configures/new
  def new
    @configure = Configure.new
  end

  # GET /configures/1/edit
  def edit
  end

  # POST /configures
  # POST /configures.json
  def create
    java_port = Configure.find params[:reasoner_id].to_i
    java_port.value = params[:reasoner_value]
    java_port.save

    require 'json'
    File.open("#{Rails.root}/config/reasoner.config", 'w') do |file|
      file.write java_port.to_json
    end

    render :json => true
  end

  # PATCH/PUT /configures/1
  # PATCH/PUT /configures/1.json
  def update
    respond_to do |format|
      if @configure.update(configure_params)
        format.html { redirect_to @configure, notice: 'Configure was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @configure.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /configures/1
  # DELETE /configures/1.json
  def destroy
    @configure.destroy
    respond_to do |format|
      format.html { redirect_to configures_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_configure
      @configure = Configure.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def configure_params
      params.require(:configure).permit(:key, :value)
    end
end
