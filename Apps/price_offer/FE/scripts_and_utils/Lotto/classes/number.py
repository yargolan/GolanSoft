class Number:

    def __init__(self, value, instances):
        self.value = value
        self.instances = instances


    def get_value(self):
        return self.value


    def get_instances(self):
        return self.instances


    def increment_instances(self):
        self.instances += 1
