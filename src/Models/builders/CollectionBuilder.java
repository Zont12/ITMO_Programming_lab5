package Models.builders;

import exceptions.IncorrectInputEX;

public abstract class CollectionBuilder<Element> {
    public abstract Element build() throws IncorrectInputEX;
}
