
json.array! @consultants.each do |item|
  buffer = @param_values.where :consultant_id => item.id
  unless buffer.blank?
    json.id item.id
    json.fio item.short_name
    json.main do
      buffer.each do |b|
        json.indPlan b.value if b.param_id == 1
        json.implPlan b.value if b.param_id == 2
        json.impl b.value if b.param_id == 3
        json.avCheck b.value if b.param_id == 4
        json.itemsCount b.value if b.param_id == 5
        json.totalChecks b.value if b.param_id == 6
      end
    end
    json.additional do
        json.holiday buffer.include?(7) ? buffer.find(7).value.to_i : false
        json.hospital buffer.include?(8) ? buffer.find(8).value.to_i : false
        json.mleave buffer.include?(9) ? buffer.find(8).value.to_i : false
        json.exp buffer.include?(10) ? buffer.find(8).value.to_i : false
        json.dismissal buffer.include?(11) ? buffer.find(8).value.to_i : false
    end
    problems = []
    AnalysisProblemConnect.where(:analysis_id => @analyse.id, :consultant_id => item.id).each { |it| problems.push it.problem.description }
    json.problems do
      json.value do
        json.array! problems.uniq
      end
    end
    solutions = []
    AnalysisSolutionConnect.where(:analysis_id => @analyse.id, :consultant_id => item.id).each { |it| solutions.push it.solution.description }
    json.solutions do
      json.value do
        json.array! solutions.uniq
      end
    end
    json.isFinishSolution false
    json.isFinishProblem false
  end
end