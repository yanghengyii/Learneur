//create (:knowledge);
//create ()-[:preKnowledge]->();
//create ()-[:include]->();
//create ()-[:associated]->();
//create ()-[:common]->();

// clear all
match (n) detach delete n
