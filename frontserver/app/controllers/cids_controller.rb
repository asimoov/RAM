class CidsController < ApplicationController
  before_action :set_cid, only: [:show, :edit, :update, :destroy]

  # GET /cids
  # GET /cids.json
  def index
    @cids = Cid.all
  end

  # GET /cids/1
  # GET /cids/1.json
  def show
  end

  # GET /cids/new
  def new
    @cid = Cid.new
  end

  # GET /cids/1/edit
  def edit
  end

  # POST /cids
  # POST /cids.json
  def create
    @cid = Cid.new(cid_params)

    respond_to do |format|
      if @cid.save
        format.html { redirect_to @cid, notice: 'Cid was successfully created.' }
        format.json { render action: 'show', status: :created, location: @cid }
      else
        format.html { render action: 'new' }
        format.json { render json: @cid.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /cids/1
  # PATCH/PUT /cids/1.json
  def update
    respond_to do |format|
      if @cid.update(cid_params)
        format.html { redirect_to @cid, notice: 'Cid was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @cid.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /cids/1
  # DELETE /cids/1.json
  def destroy
    @cid.destroy
    respond_to do |format|
      format.html { redirect_to cids_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_cid
      @cid = Cid.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def cid_params
      params.require(:cid).permit(:code, :description)
    end
end
