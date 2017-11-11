from flask_restful import Resource, marshal_with, fields, reqparse, abort, request
from . import api
from models.models import Department, ChildDepartment
from sqlalchemy import desc

department_fields = {
    'department_id': fields.Integer,
    'activity_num': fields.Integer,
    'child_dep_num': fields.Integer,
    'creat_time': fields.Integer,
    'dep_manager_id': fields.Integer,
    'dep_name': fields.String,
    'dep_summary': fields.String,
    'image_url': fields.String,
    'member_num': fields.Integer,
    'emergency_phone': fields.String,
}

child_department_fields = {
    'child_department_id': fields.Integer,
    'department_id': fields.Integer,
    'email': fields.String,
    'name': fields.String,
    'phone': fields.String,
}

class DepartmentDetails(Resource):
    @marshal_with(department_fields)
    def get(self, department_id=None):
        department = Department.query.filter_by(department_id=department_id).first()
        if department == None:
            abort(404)
        return department

class DepartmentResource(Resource):
    @marshal_with(department_fields)
    def get(self):
        page = int(request.args.get("page"))
        print page
        if page <= 0:
            abort(404)
        pagination = Department.query.order_by(Department.department_id).paginate(page, per_page=10, error_out=True)
        return pagination

class ChildDepartmentResource(Resource):
    @marshal_with(child_department_fields)
    def get(self, department_id=None):
        if department_id == None:
            abort(404)
        child_department = ChildDepartment.query.filter_by(department_id=department_id).all()
        return child_department

#class AuditDepartment(Resource):


api.add_resource(DepartmentResource, '/department')
api.add_resource(DepartmentDetails, '/department/<int:department_id>')
api.add_resource(ChildDepartmentResource, '/department/<int:department_id>/child')
