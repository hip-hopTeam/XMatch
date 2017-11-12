from flask_restful import Api

api = Api()

from . import manager, activity, department
from models import db