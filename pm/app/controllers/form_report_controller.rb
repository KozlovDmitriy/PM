class FormReportController < ApplicationController

  def index
    @analysis = Analysis.all.order(:date).uniq!
    @consultants = Consultant.all
  end

end
