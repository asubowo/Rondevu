module Api
  class UsersController < Api::BaseController

    private

      def artist_params
        params.require(:username, :email).permit(:name)
      end

      def query_params
        params.permit(:name)
      end

  end
end