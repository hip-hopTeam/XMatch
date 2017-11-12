from flask_restful import Resource, marshal_with, fields, reqparse, abort, request
from . import api
from models.models import Activity
from sqlalchemy import desc

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
    @marshal_with(activity_fields)
    def get(self, activity_id=None):
        activity = Activity.query.filter_by(activity_id=activity_id).first()
        if activity == None:
            abort(404)
        return activity

class ActivityResource(Resource):
    @marshal_with(activity_fields)
    def get(self):
        page = int(request.args.get("page"))
        if page <= 0:
            abort(404)
        pagination = Activity.query.order_by(Activity.create_time.desc()).paginate(page, per_page=10, error_out=True)
        return pagination

api.add_resource(ActivityResource, '/activity')
api.add_resource(ActivityDetails, '/activity/<int:activity_id>')
