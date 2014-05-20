class FormReportController < ApplicationController

  def index
    @analysis = Analysis.all.order(:date).uniq!
    File.open('debug.txt', 'w') { |file| file.write @analysis.to_yaml }
    @consultants = Consultant.all
  end

  def get_report
    id = params[:analysis]

    load "#{Rails.root}/lib/write_excel.rb"

    consultants = Analysis.find(id.to_i).array

    report = WriteExcel.new consultants

    send_file report.file_name, :type => 'application/openoffice', :disposition => 'inline'
  end

end