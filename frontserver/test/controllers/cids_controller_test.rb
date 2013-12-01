require 'test_helper'

class CidsControllerTest < ActionController::TestCase
  setup do
    @cid = cids(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:cids)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create cid" do
    assert_difference('Cid.count') do
      post :create, cid: { code: @cid.code, description: @cid.description }
    end

    assert_redirected_to cid_path(assigns(:cid))
  end

  test "should show cid" do
    get :show, id: @cid
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @cid
    assert_response :success
  end

  test "should update cid" do
    patch :update, id: @cid, cid: { code: @cid.code, description: @cid.description }
    assert_redirected_to cid_path(assigns(:cid))
  end

  test "should destroy cid" do
    assert_difference('Cid.count', -1) do
      delete :destroy, id: @cid
    end

    assert_redirected_to cids_path
  end
end
