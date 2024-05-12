from django.contrib import admin
from .models import *

class PagoAdmin(admin.ModelAdmin):
    list_display = ('id_pago','fecha')
    
class ClaseAdmin(admin.ModelAdmin):
    list_display = ('id_clase','nombre','precio','descripcion','duracion')
    
class UsuarioClaseAdmin(admin.ModelAdmin):
    list_display = ('id_user_clase','id_clase','id_user')

class FacturaAdmin(admin.ModelAdmin):
    list_display = ('id_factura','total','fecha','id_user_clase','id_pago')

admin.site.register(Pago, PagoAdmin)
admin.site.register(Clase, ClaseAdmin)
admin.site.register(UsuarioClase, UsuarioClaseAdmin)
admin.site.register(Factura, FacturaAdmin)