# Generated by Django 3.2.9 on 2021-11-12 07:06

import django.core.validators
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='diary',
            fields=[
                ('diary_id', models.AutoField(primary_key=True, serialize=False)),
                ('diary_date', models.DateTimeField(auto_now_add=True)),
                ('diary_weather', models.PositiveSmallIntegerField(validators=[django.core.validators.MinValueValidator(1), django.core.validators.MaxValueValidator(4)])),
                ('diary_title', models.CharField(max_length=20)),
                ('diary_content', models.TextField(max_length=200)),
                ('diary_todayme', models.CharField(blank=True, max_length=20, null=True)),
                ('diary_tomorrowme', models.CharField(blank=True, max_length=20, null=True)),
                ('diary_img', models.ImageField(blank=True, null=True, upload_to=None)),
            ],
        ),
        migrations.CreateModel(
            name='user',
            fields=[
                ('id_user', models.AutoField(primary_key=True, serialize=False)),
                ('kakaotoken', models.BooleanField()),
            ],
        ),
        migrations.CreateModel(
            name='calendar',
            fields=[
                ('cal_todaysdate', models.DateTimeField(auto_now_add=True, primary_key=True, serialize=False)),
                ('cal_thismonth', models.IntegerField(null=True, validators=[django.core.validators.MinValueValidator(1), django.core.validators.MaxValueValidator(12)])),
                ('cal_diaryfrequency', models.IntegerField(blank=True, default=0, null=True)),
                ('diary', models.OneToOneField(null=True, on_delete=django.db.models.deletion.CASCADE, to='diary.diary')),
                ('user', models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='diary.user')),
            ],
        ),
    ]
