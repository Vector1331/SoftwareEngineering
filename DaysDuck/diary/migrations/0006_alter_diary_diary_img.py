# Generated by Django 3.2.4 on 2021-11-24 05:05

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('diary', '0005_auto_20211122_1755'),
    ]

    operations = [
        migrations.AlterField(
            model_name='diary',
            name='diary_img',
            field=models.ImageField(blank=True, null=True, upload_to=''),
        ),
    ]
