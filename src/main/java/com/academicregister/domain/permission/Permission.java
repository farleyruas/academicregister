package com.academicregister.domain.permission;

import com.academicregister.domain.resource.Resource;
import com.academicregister.domain.rol.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class Permission {
    private Rol rol;
    private Resource resource;
}
