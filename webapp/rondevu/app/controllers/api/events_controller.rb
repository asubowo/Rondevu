module Api
  class EventsController < Api::BaseController

    private

      def event_params
        #params.require(:city).permit(:host)
        params.require(:city)
        params.require(:title)
      end

      def query_params
        # this assumes that an event belongs to an user and has an :user_id
        # allowing us to filter by this
        params.permit(:city, :title)
      end

  end
end