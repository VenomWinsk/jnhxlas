import pyximport

pyximport.install()

from . import es_opt as es_opt

import2ES = es_opt.import2ES
create_max_results_window = es_opt.create_max_results_window
create_index = es_opt.create_index
setReplicas = es_opt.setReplicas
