# coding: utf-8
from sqlalchemy import BigInteger, Column, Integer, String, Text, text
from sqlalchemy.dialects.mysql.types import BIT
from sqlalchemy.ext.declarative import declarative_base
from . import db

Base = declarative_base(cls=db.Model)
metadata = Base.metadata


class Activity(Base):
    __tablename__ = 'activity'

    activity_id = Column(BigInteger, primary_key=True)
    activity_name = Column(String(255))
    address = Column(String(255))
    content = Column(Text)
    create_time = Column(BigInteger, nullable=False)
    end_time = Column(BigInteger, nullable=False)
    image_url = Column(String(255))
    manager_phone = Column(String(255))
    start_time = Column(BigInteger, nullable=False)
    apply_num = Column(Integer, nullable=False)
    sign_in = Column(Integer, nullable=False)


class ActivityApply(Base):
    __tablename__ = 'activity_apply'

    activity_apply_id = Column(BigInteger, primary_key=True)
    activity_id = Column(BigInteger, nullable=False)
    apply_time = Column(BigInteger, nullable=False)
    sign_in_time = Column(BigInteger, nullable=False)
    state = Column(Integer, nullable=False)
    user_id = Column(BigInteger, nullable=False)
    user_name = Column(String(255))


class AppNotice(Base):
    __tablename__ = 'app_notice'

    app_notice_id = Column(BigInteger, primary_key=True)
    content = Column(Text)
    create_time = Column(BigInteger, nullable=False)
    department_id = Column(BigInteger, nullable=False)
    title = Column(String(255))
    type = Column(Integer, nullable=False)


class Attendance(Base):
    __tablename__ = 'attendance'

    attendance_id = Column(BigInteger, primary_key=True)
    create_time = Column(BigInteger, nullable=False)
    department_id = Column(BigInteger, nullable=False)
    end_time = Column(Integer, nullable=False)
    start_time = Column(Integer, nullable=False)
    user_id = Column(BigInteger, nullable=False)
    week = Column(Integer, nullable=False)
    weekend = Column(Integer, nullable=False)


class ChildDepartment(Base):
    __tablename__ = 'child_department'

    child_department_id = Column(BigInteger, primary_key=True)
    department_id = Column(BigInteger, nullable=False)
    email = Column(String(255))
    name = Column(String(255))
    phone = Column(String(255))


class Course(Base):
    __tablename__ = 'course'

    course_id = Column(BigInteger, primary_key=True)
    end_time = Column(Integer, nullable=False)
    end_week = Column(Integer, nullable=False)
    is_double = Column(BIT(1), nullable=False)
    is_single = Column(BIT(1), nullable=False)
    name = Column(String(255))
    start_time = Column(Integer, nullable=False)
    start_week = Column(Integer, nullable=False)
    weekend = Column(Integer, nullable=False)
    xuenian = Column(Integer, nullable=False)
    year = Column(Integer, nullable=False)


class DepManager(Base):
    __tablename__ = 'dep_manager'
    role_manager = 1
    role_supervisor = 2
    dep_manager_id = Column(BigInteger, primary_key=True)
    dep_manager_account = Column(String(255), unique=True)
    department_id = Column(BigInteger, nullable=False)
    email = Column(String(255))
    manager_name = Column(String(255))
    password = Column(String(255))
    phone_num = Column(String(255))
    manager_summary = Column(String(255))
    role = Column(Integer, nullable=False)


class DepMember(Base):
    __tablename__ = 'dep_member'

    dep_member_id = Column(BigInteger, primary_key=True)
    dep_id = Column(BigInteger, nullable=False)
    join_time = Column(BigInteger, nullable=False)
    role = Column(String(255))
    user_id = Column(BigInteger, nullable=False)
    state = Column(Integer, nullable=False)


class Department(Base):
    __tablename__ = 'department'

    department_id = Column(BigInteger, primary_key=True)
    activity_num = Column(Integer, nullable=False)
    child_dep_num = Column(Integer, nullable=False)
    creat_time = Column(BigInteger, nullable=False)
    dep_manager_id = Column(BigInteger, nullable=False)
    dep_name = Column(String(255), unique=True)
    dep_summary = Column(Text)
    image_url = Column(String(255))
    member_num = Column(Integer, nullable=False)
    emergency_phone = Column(String(255))
    state = Column(Integer, nullable=False, server_default=text("'0'"))


class EmailNotice(Base):
    __tablename__ = 'email_notice'

    email_notice_id = Column(BigInteger, primary_key=True)
    content = Column(String(255))
    create_time = Column(BigInteger, nullable=False)
    title = Column(String(255))
    to_email_url = Column(String(255))


class Message(Base):
    __tablename__ = 'message'

    message_id = Column(BigInteger, primary_key=True)
    content = Column(String(255))
    create_time = Column(BigInteger, nullable=False)
    state = Column(Integer, nullable=False)
    title = Column(String(255))
    to_users = Column(String(255))
    user_sum = Column(Integer, nullable=False)


class User(Base):
    __tablename__ = 'user'

    user_id = Column(BigInteger, primary_key=True)
    bind_phone = Column(Integer, nullable=False)
    college = Column(String(255))
    email = Column(String(255))
    passwd = Column(String(255))
    phone_num = Column(String(255))
    sex = Column(Integer, nullable=False)
    stu_no = Column(String(255), unique=True)
    username = Column(String(255))
