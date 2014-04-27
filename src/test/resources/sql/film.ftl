select film_title
   from film_table
  where last_name  = "ford"
    and first_name = "harrison"
<#if film_year??>
<#include "film_subselect.ftl">
</#if>
<#if titles??>
    and film_title in (
<#list titles as  title>
    "${title}"<#if title_has_next>,</#if>
</#list>
    )
</#if>