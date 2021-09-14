from django.db import models


class User(models.Model):
    """
    用户表
    """
    user_id = models.AutoField(primary_key=True)
    username = models.CharField(max_length=20)
    password = models.CharField(max_length=20)
    user_description = models.CharField(max_length=100)

    def str(self):
        return self.username

    class Meta:
        db_table = "user"


class Unit(models.Model):
    """
    单位表
    """
    unit_id = models.AutoField(primary_key=True)
    unit_name = models.CharField(max_length=20)
    unit_description = models.CharField(max_length=100, null=True, blank=True)

    # created_by = models.CharField()
    # create_time = models.TimeField()
    # updated_by = models.CharField()
    # updated_time = models.DateField()

    class Meta:
        db_table = 'unit'
