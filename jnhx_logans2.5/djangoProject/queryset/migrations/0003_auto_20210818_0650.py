# Generated by Django 3.2.6 on 2021-08-18 06:50

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('queryset', '0002_auto_20210818_0051'),
    ]

    operations = [
        migrations.AlterField(
            model_name='bookmark',
            name='createtime',
            field=models.DateTimeField(default=datetime.datetime(2021, 8, 18, 6, 50, 27, 599143)),
        ),
        migrations.AlterField(
            model_name='bookmark',
            name='mark_pid',
            field=models.IntegerField(blank=True, null=True),
        ),
        migrations.AlterField(
            model_name='offline',
            name='edatetime',
            field=models.DateTimeField(default=datetime.datetime(2021, 8, 18, 6, 50, 27, 599393)),
        ),
        migrations.AlterField(
            model_name='offline',
            name='sdatetime',
            field=models.DateTimeField(default=datetime.datetime(2021, 8, 18, 6, 50, 27, 599382)),
        ),
    ]
