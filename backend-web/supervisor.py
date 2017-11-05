#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
api = Api(app)
db = SQLAlchemy(app)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://zsqxmatch:13110521828@120.25.241.49:3306/xmatch'

db.create_all()
parser = reqparse.RequestParser()
parser.add_argument('user_id',type=int )
parser.add_argument('bind_phone', type=int )
parser.add_argument('college', type=str )
parser.add_argument('passwd', type=str )
parser.add_argument('email', type=str )
parser.add_argument('phone_num', type=str )
parser.add_argument('username', type=str )
parser.add_argument('stu_no', type=str )
parser.add_argument('sex', type=int )

class User(db.Model):
    __tablename__ = 'user'
    user_id = db.Column(db.Integer, primary_key=True, nullable=False)
    bind_phone = db.Column(db.Integer)
    college = db.Column(db.String(255))
    passwd = db.Column(db.String(255))
    email = db.Column(db.String(255))
    phone_num = db.Column(db.String(255))
    username = db.Column(db.String(255))
    stu_no = db.Column(db.String(255))
    sex = db.Column(db.Integer, nullable=False)

    resource_fields = {
        'user_id': fields.Integer,
        'bind_phone': fields.Integer,
        'college': fields.String,
        'passwd': fields.String,
        'email': fields.String,
        'phone_num': fields.String,
        'username': fields.String,
        'stu_no': fields.String,
        'sex': fields.Integer,
    }

class Register(Resource):
    def post(self):
        args = parser.parse_args()
        user_id = args['user_id']
        bind_phone = args['bind_phone']
        college = args['college']
        passwd = args['passwd']
        email = args['email']
        phone_num = args['phone_num']
        username = args['username']
        stu_no = args['stu_no']
        sex = args['sex']
        # mail examination
        # exist_user = User.query.filter_by(mail=mail).first()
        # if exist_user is not None:
        #     return 'User Exists', 401
        # # phone examination
        # exist_user = User.query.filter_by(phone=phone).first()
        # if exist_user is not None:
        #     return 'User Exists', 401

        new_user = User(user_id=user_id, bind_phone=bind_phone, college=college, 
                        passwd=passwd, email=email, phone_num=phone_num, 
                        username=username, stu_no=stu_no, sex=sex)
        db.session.add(new_user)
        db.session.commit()
        return 'Successfully', 200

class Delete(Resource):
    def post(self):
        args = parser.parse_args()
        user_id = args['user_id']
        user = User.query.filter_by(user_id=user_id).first()
        if user is None:
            return 'User not found', 401
        db.session.delete(user)
        db.session.commit()

class Alter(Resource):
    def post(self):
        args = parser.parse_args()
        user_id = args['user_id']
        user = User.query.filter_by(user_id=user_id).first()
        if user is None:
            return 'User not found  ', 401

        bind_phone = args['bind_phone']
        if bind_phone is not None:
            user.bind_phone = bind_phone
        
        college = args['college']
        if college is not None:
            user.college = college
        
        passwd = args['passwd']
        if passwd is not None:
            user.passwd = passwd
        
        email = args['email']
        if email is not None:
            user.email = email
        
        phone_num = args['phone_num']
        if phone_num is not None:
            user.phone_num = phone_num

        username = args['username']
        if username is not None:
            user.username = username

        stu_no = args['stu_no']
        if stu_no is not None:
            user.stu_no = stu_no
        
        sex = args['sex']
        if sex is not None:
            user.sex = sex

        db.session.commit()

class Query(Resource):
    @marshal_with(User.resource_fields, envelope='resource')
    def get(self, id):
        user = User.query.get(id)
        return user

api.add_resource(Register, '/register')
api.add_resource(Delete, '/delete')
api.add_resource(Alter, '/alter')
api.add_resource(Query, '/query/<string:id>')

app.run(debug=True)
