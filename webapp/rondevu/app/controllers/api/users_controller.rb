module Api
  class UsersController < Api::BaseController

    private

      def user_params
        JSON.parse params.require(:user)#.permit(:name)
      end

      def query_params
        params.permit(:name)
      end

  end
end