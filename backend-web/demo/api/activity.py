from flask_restful import Resource, marshal_with, fields, reqparse, request
from . import format_response_with, success, failure, unauthorized, api, desc, db
from models.models import Activity

activity_fields = {
    'activity_id': fields.Integer,
    'activity_name': fields.String,
    'address': fields.String,
    'content': fields.String,
    'create_time': fields.Integer,
    'end_time': fields.Integer,
    'image_url': fields.String,
    'manager_phone': fields.String,
    'start_time': fields.Integer,
    'apply_num': fields.Integer,
    'sign_in': fields.Integer,
}

class ActivityDetails(Resource):
    @format_response_with(activity_fields)
    def get(self, activity_id=None):
        # status: if the user is a supervisor, status is True
        status = isLoggedIn()
        if not status:
            return unauthorized()

        if activity_id == None:
            failure(-1,'activity_id is required')
        activity = Activity.query.filter_by(activity_id=activity_id).first()
        if activity == None:
            failure(-1,'there is no such activity')
        return success(activity)

class ActivityResource(Resource):
    @format_response_with(activity_fields)
    def get(self):
        # status: if the user is a supervisor, status is True
        status = isLoggedIn()
        if not status:
            return unauthorized()

        try:
            page = int(request.args.get("page") or 1)
        except ValueError:
            page = 1
        if page <= 0:
            return failure(-1,'page number should be at least 1')
        pagination = Activity.query.order_by(Activity.create_time.desc()).paginate(page, per_page=10, error_out=True)
        return success(pagination)

api.add_resource(ActivityResource, '/activity', methods=['GET'])
api.add_resource(ActivityDetails, '/activity/<int:activity_id>', methods=['POST'])
