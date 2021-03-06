class ProblemsController < ApplicationController
  before_action :set_problem, only: [:show, :edit, :update, :destroy]

  # GET /problems
  # GET /problems.json
  def index
    @problems = Problem.all
  end

  # GET /problems/1
  # GET /problems/1.json
  def show
  end

  # GET /problems/new
  def new
    @problem = Problem.new
  end

  # GET /problems/1/edit
  def edit
  end

  # POST /problems
  # POST /problems.json
  def create
    @problem = Problem.new
    @problem.description = params[:problem][:description]
    @problem.problem_type = params[:problem][:problem_type]
    @problem.cut_description!

    # require_relative '../../lib/tcp_client'
    # client = TcpClient.new 50125, 'localhost'
    # client.create_new_problem @problem
    # client.close

    respond_to do |format|
      if @problem.save
        format.html { redirect_to problems_path, notice: 'Problem was successfully created.' }
        format.json { render action: 'show', status: :created, location: @problem }
      else
        format.html { render action: 'new' }
        format.json { render json: @problem.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /problems/1
  # PATCH/PUT /problems/1.json
  def update
    @problem.description = params[:problem][:description]
    @problem.problem_type = params[:problem][:problem_type]
    @problem.cut_description!
    params[:problem][:description] = @problem.description
    respond_to do |format|
      if @problem.save
        format.html { redirect_to problems_path, notice: 'Problem was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @problem.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /problems/1
  # DELETE /problems/1.json
  def destroy
    @problem.destroy
    respond_to do |format|
      format.html { redirect_to problems_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_problem
      @problem = Problem.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def problem_params
       if params[:problem][:type] == 'complex'
         params[:problem][:description] = params[:problem][:description].gsub /\s[И]$/, ''
       end
      params.require(:problem).permit(:description)
    end
end
