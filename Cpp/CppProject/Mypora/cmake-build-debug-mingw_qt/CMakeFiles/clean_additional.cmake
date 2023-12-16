# Additional clean files
cmake_minimum_required(VERSION 3.16)

if("${CONFIG}" STREQUAL "" OR "${CONFIG}" STREQUAL "Debug")
  file(REMOVE_RECURSE
  "CMakeFiles\\Mypora_autogen.dir\\AutogenUsed.txt"
  "CMakeFiles\\Mypora_autogen.dir\\ParseCache.txt"
  "Mypora_autogen"
  )
endif()
