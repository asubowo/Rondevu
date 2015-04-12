module Api
  class EventsController < Api::BaseController



    private

      def event_params
        #params.require(:city).permit(:host)
        #skip_before_filter  :verify_authenticity_token
        #params.require(:city).permit(:title)
        JSON.parse params.require(:event)#.permit(:title, :description, :host, :owner_id, :public, :location, :city, :date_time, :capacity, :category, :attending)
        
      end

      def query_params
        # this assumes that an event belongs to an user and has an :user_id
        # allowing us to filter by this
        params.permit(:city, :title)
      end

  end
end