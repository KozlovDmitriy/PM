class InputDataItemsController < ApplicationController
  before_action :set_input_data_item, only: [:show, :edit, :update, :destroy]

  # GET /input_data_items
  # GET /input_data_items.json
  def index
    @input_data_items = InputDataItem.all
  end

  # GET /input_data_items/1
  # GET /input_data_items/1.json
  def show
  end

  # GET /input_data_items/new
  def new
    @input_data_item = InputDataItem.new
  end

  # GET /input_data_items/1/edit
  def edit
  end

  # POST /input_data_items
  # POST /input_data_items.json
  def create
    @input_data_item = InputDataItem.new(input_data_item_params)

    respond_to do |format|
      if @input_data_item.save
        format.html { redirect_to @input_data_item, notice: 'Input data item was successfully created.' }
        format.json { render action: 'show', status: :created, location: @input_data_item }
      else
        format.html { render action: 'new' }
        format.json { render json: @input_data_item.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /input_data_items/1
  # PATCH/PUT /input_data_items/1.json
  def update
    respond_to do |format|
      if @input_data_item.update(input_data_item_params)
        format.html { redirect_to @input_data_item, notice: 'Input data item was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @input_data_item.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /input_data_items/1
  # DELETE /input_data_items/1.json
  def destroy
    @input_data_item.destroy
    respond_to do |format|
      format.html { redirect_to input_data_items_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_input_data_item
      @input_data_item = InputDataItem.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def input_data_item_params
      params.require(:input_data_item).permit(:consultant_id, :input_data_id)
    end
end
