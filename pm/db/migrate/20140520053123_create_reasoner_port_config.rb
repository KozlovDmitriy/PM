class CreateReasonerPortConfig < ActiveRecord::Migration
  def change
    java_port = Configure.create :key => 'reasoner_port', :value => '50125', :id => 1
  end
end
