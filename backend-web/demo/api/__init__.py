from flask_restful import Api, fields
from models import db
from sqlalchemy import desc
from models.response import *

api = Api()

from . import manager, activity, department, login
