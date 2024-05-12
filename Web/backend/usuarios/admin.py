from django.contrib import admin
from .models import *

class RolAdmin (admin.ModelAdmin):
    list_display = ('id_rol','nombre_rol')
    

class UsuarioAdmin (admin.ModelAdmin):
    list_display = ('id_user','id_rol','nombre','apellido','fec_nac','email','tel')

admin.site.register(Rol, RolAdmin)
admin.site.register(Usuario, UsuarioAdmin)
