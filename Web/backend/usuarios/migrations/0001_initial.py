# Generated by Django 5.0.6 on 2024-05-12 00:46

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Rol',
            fields=[
                ('id_rol', models.AutoField(primary_key=True, serialize=False)),
                ('nombre_rol', models.CharField(max_length=5)),
            ],
            options={
                'verbose_name': 'Rol de usuario',
                'verbose_name_plural': 'Roles',
                'db_table': 'Rol',
            },
        ),
    ]
