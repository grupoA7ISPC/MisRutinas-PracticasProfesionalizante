from django.contrib import admin
from .models import *

class ClaseAdmin(admin.ModelAdmin):
    list_display = ('id_clase','nombre','precio','descripcion','duracion')
    
class UsuarioClaseAdmin(admin.ModelAdmin):
    list_display = ('id_user_clase','id_clase','id_user')

class PagoAdmin(admin.ModelAdmin):
    list_display = ('id_pago','total','fecha','id_user_clase')

admin.site.register(Clase, ClaseAdmin)
admin.site.register(UsuarioClase, UsuarioClaseAdmin)
admin.site.register(Pago, PagoAdmin)