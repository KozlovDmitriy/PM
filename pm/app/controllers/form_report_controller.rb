class FormReportController < ApplicationController

  def index
    @analysis = Analysis.all.order(:date).uniq!
  end

end
