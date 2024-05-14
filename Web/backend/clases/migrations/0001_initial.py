# Generated by Django 5.0.6 on 2024-05-12 03:48

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('usuarios', '0002_usuario'),
    ]

    operations = [
        migrations.CreateModel(
            name='Clase',
            fields=[
                ('id_clase', models.AutoField(primary_key=True, serialize=False)),
                ('nombre', models.CharField(max_length=45)),
                ('precio', models.DecimalField(decimal_places=2, max_digits=8)),
                ('descripcion', models.TextField(max_length=400)),
                ('duracion', models.CharField(max_length=150)),
            ],
            options={
                'verbose_name': 'Clase que da el GYM',
                'verbose_name_plural': 'Clases',
                'db_table': 'Clase',
            },
        ),
        migrations.CreateModel(
            name='Pago',
            fields=[
                ('id_pago', models.AutoField(primary_key=True, serialize=False)),
                ('fecha', models.DateTimeField(auto_now_add=True)),
            ],
            options={
                'verbose_name': 'Pago de clase',
                'verbose_name_plural': 'Pagos de clases',
                'db_table': 'Pago',
            },
        ),
        migrations.CreateModel(
            name='UsuarioClase',
            fields=[
                ('id_user_clase', models.AutoField(primary_key=True, serialize=False)),
                ('id_clase', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='clases.clase')),
                ('id_user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='usuarios.usuario')),
            ],
            options={
                'verbose_name': 'Clase del usuario',
                'verbose_name_plural': 'UsuariosClases',
                'db_table': 'UsuarioClase',
            },
        ),
        migrations.CreateModel(
            name='Factura',
            fields=[
                ('id_factura', models.AutoField(primary_key=True, serialize=False)),
                ('total', models.DecimalField(decimal_places=2, max_digits=8)),
                ('fecha', models.DateTimeField(auto_now_add=True)),
                ('id_pago', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='clases.pago')),
                ('id_user_clase', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='clases.usuarioclase')),
            ],
            options={
                'verbose_name': 'Factura del pago de clase',
                'verbose_name_plural': 'Facturas',
                'db_table': 'Factura',
            },
        ),
    ]