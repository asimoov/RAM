class ResearchesController < ApplicationController
  before_action :set_research, only: [:show, :edit, :update, :destroy]
  before_action :load_research, only: :create
  load_and_authorize_resource

  # GET /researches
  # GET /researches.json
  def index
    @researches = Research.all
  end

  # GET /researches/1
  # GET /researches/1.json
  def show
  end

  # GET /researches/new
  def new
    @research = Research.new
  end

  # GET /researches/1/edit
  def edit
  end

  # POST /researches
  # POST /researches.json
  def create
    respond_to do |format|
      if @research.save
        format.html { redirect_to @research, notice: 'Research was successfully created.' }
        format.json { render action: 'show', status: :created, location: @research }
      else
        format.html { render action: 'new' }
        format.json { render json: @research.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /researches/1
  # PATCH/PUT /researches/1.json
  def update
    respond_to do |format|
      if @research.update(research_params)
        format.html { redirect_to @research, notice: 'Research was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @research.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /researches/1
  # DELETE /researches/1.json
  def destroy
    @research.destroy
    respond_to do |format|
      format.html { redirect_to researches_url }
      format.json { head :no_content }
    end
  end

  def excel
    @researches = Research.all
    respond_to do |format|
      format.xls
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_research
      @research = Research.find(params[:id])
    end

    def load_research
      @research = Research.new(research_params)
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def research_params
      params.require(:research).permit(:handbook, :name, :cns, :sex, :weight, :height, :color, :unit, :bed, :admission, :hospital_id, :cause_id)
    end
end
