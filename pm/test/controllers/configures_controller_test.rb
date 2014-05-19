require 'test_helper'

class ConfiguresControllerTest < ActionController::TestCase
  setup do
    @configure = configures(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:configures)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create configure" do
    assert_difference('Configure.count') do
      post :create, configure: { key: @configure.key, value: @configure.value }
    end

    assert_redirected_to configure_path(assigns(:configure))
  end

  test "should show configure" do
    get :show, id: @configure
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @configure
    assert_response :success
  end

  test "should update configure" do
    patch :update, id: @configure, configure: { key: @configure.key, value: @configure.value }
    assert_redirected_to configure_path(assigns(:configure))
  end

  test "should destroy configure" do
    assert_difference('Configure.count', -1) do
      delete :destroy, id: @configure
    end

    assert_redirected_to configures_path
  end
end
