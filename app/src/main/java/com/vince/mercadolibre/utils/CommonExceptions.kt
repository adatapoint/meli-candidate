package com.vince.mercadolibre.utils

import com.vince.mercadolibre.utils.ConstantsHelper.INTERFACE_NOT_IMPLEMENTED_EXCEPTION_MESSAGE

class InterfaceNotImplementedException(
    className: String?,
    interfaceName: String,
) : ClassCastException(
    String.format(INTERFACE_NOT_IMPLEMENTED_EXCEPTION_MESSAGE, className, interfaceName),
)
