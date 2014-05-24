require 'test_helper'

class CasesControllerTest < ActionController::TestCase
  setup do
    @case = cases(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:cases)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create case" do
    assert_difference('Case.count') do
      post :create, case: { av_check: @case.av_check, impl: @case.impl, impl_plan: @case.impl_plan, ind_plan: @case.ind_plan, items_count: @case.items_count, problems_text: @case.problems_text, problems_uri: @case.problems_uri, solutions_text: @case.solutions_text, solutions_uri: @case.solutions_uri, total_checks: @case.total_checks, uri: @case.uri }
    end

    assert_redirected_to case_path(assigns(:case))
  end

  test "should show case" do
    get :show, id: @case
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @case
    assert_response :success
  end

  test "should update case" do
    patch :update, id: @case, case: { av_check: @case.av_check, impl: @case.impl, impl_plan: @case.impl_plan, ind_plan: @case.ind_plan, items_count: @case.items_count, problems_text: @case.problems_text, problems_uri: @case.problems_uri, solutions_text: @case.solutions_text, solutions_uri: @case.solutions_uri, total_checks: @case.total_checks, uri: @case.uri }
    assert_redirected_to case_path(assigns(:case))
  end

  test "should destroy case" do
    assert_difference('Case.count', -1) do
      delete :destroy, id: @case
    end

    assert_redirected_to cases_path
  end
end
