from flask_restful import Resource, marshal_with, fields, reqparse, abort, request
from . import api
from models.models import Department, ChildDepartment
from sqlalchemy import desc

UNDER_AUDIT = 1
APPROVED_AUDIT = 2
REJECTED_AUDIT = 3

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
    'state': fields.Integer,
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
        page = request.args.get("page")
        if page == None:
            page = 1
        page = int(page)
        if page <= 0:
            abort(404)
        pagination = Department.query.filter_by(state=APPROVED_AUDIT).order_by(Department.department_id).paginate(page, per_page=10, error_out=True)
        return pagination.items

class ChildDepartmentResource(Resource):
    @marshal_with(child_department_fields)
    def get(self, department_id=None):
        if department_id == None:
            abort(404)
        child_department = ChildDepartment.query.filter_by(department_id=department_id).all()
        return child_department

class AuditDepartment(Resource):

    @marshal_with(department_fields)
    def get(self):
        page = request.args.get("page")
        if page == None:
            page = 1
        page = int(page)
        if page <= 0:
            abort(404)
        pagination = Department.query.filter_by(state=UNDER_AUDIT).order_by(Department.department_id).paginate(page, per_page=10, error_out=True)
        return pagination.items

    @marshal_with(department_fields)
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('department_id',type=int)
        parser.add_argument('state',type=int)
        args = parser.parse_args() 
        #return args
        if args.department_id == None:
            abort(404)
        if args.state == APPROVED_AUDIT or args.state == REJECTED_AUDIT:
            department = Department.query.filter_by(department_id=args.department_id, state=UNDER_AUDIT).first()
            if department == None:
                abort(404)
            department.state = args.state
            db.session.add(department)
            db.session.commit()
        else : 
            abort(404)

api.add_resource(DepartmentResource, '/department')
api.add_resource(DepartmentDetails, '/department/<int:department_id>')
api.add_resource(ChildDepartmentResource, '/department/<int:department_id>/child')
api.add_resource(AuditDepartment, '/department/audit')
