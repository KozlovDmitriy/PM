require 'test_helper'

class ConsultantsControllerTest < ActionController::TestCase
  #setup do
  #  @consultant = consultants(:consultant_1)
  #end

  test 'should get index' do
    get :index
    assert_response :success
    assert_not_nil assigns(:consultants)
    assert_select 'h1', 'Список консультантов'
  end

  #test 'should get new' do
  #  get :new
  #  assert_response :success
  #  assert_select 'h1', 'Добавить нового консультанта'
  #end
  #
  ##test "should create consultant" do
  ##  assert_difference('Consultant.count') do
  ##    post :create, consultant: { family_name: @consultant.family_name, first_name: @consultant.first_name, second_name: @consultant.second_name }
  ##  end
  ##
  ##  assert_redirected_to consultant_path(assigns(:consultant))
  ##end
  #
  #test "should show consultant" do
  #  #get :show, id: @consultant
  #  get :show, :id => 1
  #  assert_response :success
  #  assert_select 'h1', /История работы консультанта: [А-Я][а-я]+\s[А-Я][а-я]+\s\.[А-Я][а-я]\./
  #end
  #
  #test "should get edit" do
  #  #get :edit, id: @consultant
  #  get :edit, :id => 1
  #  assert_response :success
  #  assert_select 'h1', 'Изменить консультанта'
  #end
  #
  ##test "should update consultant" do
  ##  patch :update, id: @consultant, consultant: { family_name: @consultant.family_name, first_name: @consultant.first_name, second_name: @consultant.second_name }
  ##  assert_redirected_to consultant_path(assigns(:consultant))
  ##end
  #
  ###test "should destroy consultant" do
  ###  assert_difference('Consultant.count', -1) do
  ###    delete :destroy, id: @consultant
  ###  end
  ##
  ##  assert_redirected_to consultants_path
  ##end
end
